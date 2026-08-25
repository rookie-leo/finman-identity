package br.com.finman.identity.architecture

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import org.junit.jupiter.api.Test

class HexagonalArchitectureTest {
    private val classes = ClassFileImporter().importPackages("br.com.finman.identity")

    @Test
    fun `domain e ports nao dependem das camadas externas`() {
        noClasses()
            .that().resideInAnyPackage("..domain..", "..port..")
            .should().dependOnClassesThat().resideInAnyPackage("..application..", "..adapters..")
            .check(classes)
    }

    @Test
    fun `application nao depende de adapters`() {
        noClasses()
            .that().resideInAPackage("..application..")
            .should().dependOnClassesThat().resideInAPackage("..adapters..")
            .check(classes)
    }
}
