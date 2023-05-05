package bankaccount.config

import bankaccount.infrastructure.repository.InMemoryAccountRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PersistenceConfig {

    @Bean
    fun accountRepository() = InMemoryAccountRepository()
}