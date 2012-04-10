package fr.fsh.todos;

import com.beust.jcommander.JCommander;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * @author fcamblor@4sh.fr
 */
public class CliOptionsTest {

    // FIXME : un-ignore this test for step 1
//    @Ignore("Ignoring this test before step 1...")
    @Test
    public void shouldHttpPortBeSetTo8086ByDefault(){
        CliOptions cliOptions = new CliOptions();
        String[] args = new String[]{};
        new JCommander(cliOptions, args);
        // FIXME: remove hyphen for step 2
        assertThat(cliOptions.httpPort(), is(equalTo(-8086)));
    }

    @Test
    public void shouldHttpPortBeSetWhenPassedAsParam(){
        CliOptions cliOptions = new CliOptions();
        String[] args = new String[]{ "-httpPort", "666"};
        new JCommander(cliOptions, args);
        assertThat(cliOptions.httpPort(), is(equalTo(666)));
    }
}
