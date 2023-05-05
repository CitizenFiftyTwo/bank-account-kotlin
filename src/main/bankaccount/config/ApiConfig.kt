package bankaccount.config

import bankaccount.domain.command.DepositOperation
import bankaccount.infrastructure.api.AccountAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApiConfig {

    @Bean
    fun accountAdapter(
        depositOperation: DepositOperation
    ) = AccountAdapter(depositOperation)
}