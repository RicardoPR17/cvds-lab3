package edu.eci.cvds.tdd.registry;

import org.junit.Assert;
import org.junit.Test;

public class RegistryTest {
    private Registry registry = new Registry();

    @Test
    public void validateRegistryResult() {
        Person person = new Person("Pepe", 123, 20, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.VALID, result);
    }
    // TODO Complete with more test cases
    @Test
    public void registerVoter_PersonaInvalidaMuerta_VotoNoValido() {
        Person person = new Person("Pipo", 124, 25, Gender.MALE, false);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.DEAD, result);
    }

    @Test
    public void registerVoter_PersonaInvalidaMenor_VotoNoValido() {
        Person person = new Person("Juan", 125, 10, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.UNDERAGE, result);
    }

    @Test
    public void registerVoter_PersonaInvalidaEdadIncorrecta_VotoNoValido() {
        Person person = new Person("Maria", 126, -1, Gender.FEMALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void registerVoter_PersonaInvalidaIdDuplicada_VotoNoValido() {
        Person person = new Person("Pepe", 123, 20, Gender.MALE, true);
        Person person2 = new Person("Juana", 123, 24, Gender.FEMALE, true);
        registry.registerVoter(person);
        RegisterResult result = registry.registerVoter(person2);
        Assert.assertEquals(RegisterResult.DUPLICATED, result);
    }
}