package us.mavelo

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class DatabasePropertiesTest {

	@Test
	fun loadDatabaseProperties_withValidPropertiesFile_loadsSuccessfully() {
		val properties = loadDatabasePropertiesFromResource("application-test.properties")

		assertEquals("jdbc:postgresql://localhost:5432/test_pwhl", properties["db.url"])
		assertEquals("test_user", properties["db.username"])
		assertEquals("test_password", properties["db.password"])
	}

	@Test
	fun loadDatabaseProperties_withEmptyPassword_loadsSuccessfully() {
		val properties = loadDatabasePropertiesFromResource("application-empty-password.properties")

		assertEquals("jdbc:postgresql://localhost:5432/test_pwhl", properties["db.url"])
		assertEquals("test_user", properties["db.username"])
		assertEquals("", properties["db.password"])
	}

	@Test
	fun loadDatabaseProperties_withMissingPasswordProperty_doesNotThrow() {
		val properties = loadDatabasePropertiesFromResource("application-missing-password.properties")

		assertEquals("jdbc:postgresql://localhost:5432/test_pwhl", properties["db.url"])
		assertEquals("test_user", properties["db.username"])
		// Password property should not exist or be null
		assertTrue(!properties.containsKey("db.password"))
	}

	@Test
	fun loadDatabaseProperties_loadsAllPropertiesAsStrings() {
		val properties = loadDatabasePropertiesFromResource("application-test.properties")

		// All values should be strings
		for ((key, value) in properties) {
			assertEquals(String::class, value::class, "Property $key should be a String")
		}
	}

	@Test
	fun loadDatabaseProperties_preservesPropertyNames() {
		val properties = loadDatabasePropertiesFromResource("application-test.properties")

		assertTrue(properties.containsKey("db.url"), "Should contain db.url key")
		assertTrue(properties.containsKey("db.username"), "Should contain db.username key")
		assertTrue(properties.containsKey("db.password"), "Should contain db.password key")
	}

	@Test
	fun loadDatabaseProperties_withMissingPropertiesFile_throwsIllegalStateException() {
		assertFailsWith<IllegalStateException>(
			message = "Should throw IllegalStateException when properties file is not found"
		) {
			loadDatabasePropertiesFromResource("nonexistent-file.properties")
		}
	}

	@Test
	fun dbConfigExtraction_extractsUrlCorrectly() {
		val dbUrl = extractDbUrl("application-test.properties")
		assertEquals("jdbc:postgresql://localhost:5432/test_pwhl", dbUrl)
	}

	@Test
	fun dbConfigExtraction_extractsUsernameCorrectly() {
		val dbUsername = extractDbUsername("application-test.properties")
		assertEquals("test_user", dbUsername)
	}

	@Test
	fun dbConfigExtraction_extractsPasswordCorrectly() {
		val dbPassword = extractDbPassword("application-test.properties")
		assertEquals("test_password", dbPassword)
	}

	@Test
	fun dbConfigExtraction_defaultsPasswordToEmptyString() {
		val dbPassword = extractDbPassword("application-missing-password.properties")
		assertEquals("", dbPassword)
	}

	@Test
	fun dbConfigExtraction_throwsWhenUrlIsMissing() {
		assertFailsWith<IllegalStateException>(
			message = "Should throw IllegalStateException when db.url is missing"
		) {
			extractDbUrl("application-missing-url.properties")
		}
	}

	@Test
	fun dbConfigExtraction_throwsWhenUsernameIsMissing() {
		assertFailsWith<IllegalStateException>(
			message = "Should throw IllegalStateException when db.username is missing"
		) {
			extractDbUsername("application-missing-username.properties")
		}
	}

	// ========== Helper Functions ==========

	private fun loadDatabasePropertiesFromResource(resourceName: String): Map<String, String> {
		val classLoader = DatabasePropertiesTest::class.java.classLoader
		val resourceUrl = classLoader.getResource(resourceName)
			?: throw IllegalStateException("$resourceName not found in classpath")

		val properties = java.util.Properties()
		properties.load(resourceUrl.openStream())
		return properties.toMap().mapKeys { it.key.toString() }.mapValues { it.value.toString() }
	}

	private fun extractDbUrl(resourceName: String): String {
		val properties = loadDatabasePropertiesFromResource(resourceName)
		return properties["db.url"] ?: throw IllegalStateException("db.url not configured in $resourceName")
	}

	private fun extractDbUsername(resourceName: String): String {
		val properties = loadDatabasePropertiesFromResource(resourceName)
		return properties["db.username"] ?: throw IllegalStateException("db.username not configured in $resourceName")
	}

	private fun extractDbPassword(resourceName: String): String {
		val properties = loadDatabasePropertiesFromResource(resourceName)
		return properties["db.password"] ?: ""
	}
}


