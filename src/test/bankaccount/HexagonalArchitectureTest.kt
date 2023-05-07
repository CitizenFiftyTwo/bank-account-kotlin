package bankaccount

import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses

@AnalyzeClasses(
    packages = ["bankaccount"],
    importOptions = [ImportOption.DoNotIncludeTests::class]
)
class HexagonalArchitectureTest {

    @ArchTest
    val `domain classes should only access to domain classes` =
        noClasses().that().resideInAPackage("..domain..")
            .should()
            .accessClassesThat()
            .resideOutsideOfPackages(
                "..domain..",
                "kotlin..",
                "java.lang..",
                "java.util..",
                "java.io..",
                "java.math.."
            )

}