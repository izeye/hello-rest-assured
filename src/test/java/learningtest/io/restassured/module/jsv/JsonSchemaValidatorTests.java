package learningtest.io.restassured.module.jsv;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link JsonSchemaValidator}.
 *
 * @author Johnny Lim
 */
class JsonSchemaValidatorTests {

    @Test
    void matchesJsonSchemaInClasspath() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = new Person("Johnny", "Lim");
        String json = objectMapper.writeValueAsString(person);
        System.out.println(json);

        // The schema has been generated with the following command:
        // quicktype --lang schema --src-lang json --out person-schema.json person.json
        String schemaPath = "person-schema.json";

        // Note that Hamcrest should be used for better assertion output.
        MatcherAssert.assertThat(json, JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
    }

    record Person(String firstName, String lastName) {
    }

}
