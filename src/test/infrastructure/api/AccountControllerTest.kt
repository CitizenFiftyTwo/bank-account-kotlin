package infrastructure.api

import bankaccount.BankAccountApplication
import bankaccount.infrastructure.api.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal

@WebMvcTest(AccountController::class)
@ContextConfiguration(classes = [BankAccountApplication::class])
class AccountControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var jsonMapper: ObjectMapper

    @MockkBean
    private lateinit var accountAdapter: AccountAdapter

    private val expectedAccount = AccountView(
        balance = BigDecimal(24),
        transactions = listOf(
            TransactionView(
                type = "DEPOSIT",
                amount = BigDecimal(24)
            )
        )
    )

    @Test
    fun `should make a deposit`() {
        every { accountAdapter.deposit(any()) } returns expectedAccount

        mockMvc.perform(
            post("/accounts/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(BigDecimal(24).toString())
        ).andExpect(status().isOk)
            .andExpect(
                content().json(
                    jsonMapper.writeValueAsString(expectedAccount)
                )
            )

        verify { accountAdapter.deposit(BigDecimal(24)) }
    }
}