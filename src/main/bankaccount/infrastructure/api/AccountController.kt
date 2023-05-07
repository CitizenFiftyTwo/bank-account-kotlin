package bankaccount.infrastructure.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RestController
@RequestMapping("/accounts")
class AccountController(
    private val accountAdapter: AccountAdapter,
) {
    @PostMapping("/deposit")
    @ResponseStatus(HttpStatus.OK)
    fun deposit(@RequestBody amount: BigDecimal): ResponseEntity<AccountView> =
        ResponseEntity.ok(this.accountAdapter.deposit(amount))
}