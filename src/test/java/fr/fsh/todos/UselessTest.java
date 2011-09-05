package fr.fsh.todos;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author fcamblor
 */
public class UselessTest {

    @Test
    public void shouldNeverBeSuccessful(){
        assertThat(false, is(equalTo(false)));
    }
}
