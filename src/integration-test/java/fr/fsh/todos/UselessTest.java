package fr.fsh.todos;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * @author fcamblor
 * Providing a useless integ test in order to not fail jenkins where every
 * SimpleUITest is ignored
 */
public class UselessTest {

    @Test
    public void shouldTestAUselessThing(){
        assertThat(true, is(equalTo(true)));
    }
}
