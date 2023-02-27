package edu.eci.cvds.tdd.registry;

import java.util.*;

public class Registry {
    private ArrayList<Integer> identificaciones = new ArrayList<>();
    public RegisterResult registerVoter(Person p) {
        if (p.isAlive()) {
            if (p.getAge() <= 0) {
                return RegisterResult.INVALID_AGE;
            } else if (p.getAge() >= 18) {
                if (identificaciones.contains(p.getId())) {
                    return RegisterResult.DUPLICATED;
                } else {
                    identificaciones.add(p.getId());
                    return RegisterResult.VALID;
                }
            }
            return RegisterResult.UNDERAGE;
        }
        return RegisterResult.DEAD;
    }
}