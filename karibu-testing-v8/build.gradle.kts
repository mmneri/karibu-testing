dependencies {
    compile("com.vaadin:vaadin-server:${ext["vaadin8_version"]}")
    compile(project(":mock-servlet-environment"))

    testCompile("com.github.mvysny.dynatest:dynatest-engine:${ext["dynatest_version"]}")
    compile("org.jetbrains.kotlin:kotlin-test")
    testCompile("org.slf4j:slf4j-simple:1.7.25")
    testCompile("com.github.vok.karibudsl:karibu-dsl-v8:0.4.11")

    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

val configureBintray = ext["configureBintray"] as (artifactId: String) -> Unit
configureBintray("karibu-testing-v8")
