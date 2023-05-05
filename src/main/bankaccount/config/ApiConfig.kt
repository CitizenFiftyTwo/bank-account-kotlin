package bankaccount.config

import bankaccount.domain.command.DepositOperation
import bankaccount.domain.command.WithdrawOperation
import bankaccount.infrastructure.api.AccountAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApiConfig {

    @Bean
    fun accountAdapter(
        depositOperation: DepositOperation,
        withdrawOperation: WithdrawOperation,
    ) = AccountAdapter(depositOperation, withdrawOperation)
}