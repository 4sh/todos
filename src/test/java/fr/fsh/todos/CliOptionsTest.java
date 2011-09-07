package fr.fsh.todos;

import com.beust.jcommander.JCommander;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * @author fcamblor
 */
public class CliOptionsTest {

    @Test
    public void shouldHttpPortBeSetTo8086ByDefault(){
        CliOptions cliOptions = new CliOptions();
        String[] args = new String[]{};
        new JCommander(cliOptions, args);
        assertThat(cliOptions.httpPort(), is(equalTo(8086)));
    }

    @Test
    public void shouldHttpPortBeSetWhenPassedAsParam(){
        CliOptions cliOptions = new CliOptions();
        String[] args = new String[]{ "-httpPort", "666"};
        new JCommander(cliOptions, args);
        assertThat(cliOptions.httpPort(), is(equalTo(666)));
    }
}
