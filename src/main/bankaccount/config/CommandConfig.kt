package bankaccount.config

import bankaccount.domain.command.DepositOperation
import bankaccount.infrastructure.repository.InMemoryAccountRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommandConfig {
    @Bean
    fun depositOperation(accountRepository: InMemoryAccountRepository) = DepositOperation(accountRepository)
}