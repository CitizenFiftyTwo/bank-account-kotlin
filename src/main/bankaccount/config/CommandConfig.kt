package bankaccount.config

import bankaccount.domain.command.DepositOperation
import bankaccount.domain.command.WithdrawOperation
import bankaccount.infrastructure.repository.InMemoryAccountRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommandConfig {
    @Bean
    fun depositOperation(accountRepository: InMemoryAccountRepository) = DepositOperation(accountRepository)

    @Bean
    fun withdrawOperation(accountRepository: InMemoryAccountRepository) = WithdrawOperation(accountRepository)
}