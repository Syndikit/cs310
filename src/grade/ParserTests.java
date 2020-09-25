package grade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

// Do not modify.
@RunWith(Parameterized.class)
public class ParserTests {
	@Parameters(name="{index}: {0}")
	public static java.util.Collection<Object[]> data() {
		return java.util.Arrays.asList(new Object[][] {
			{ "test 1?" , true },
			{ "test 0?" , false },
			{ "test 1'?" , false },
			{ "test 0'?" , true },

			{ "test 1 -> 1?" , true },
			{ "test 1 -> 0?" , false },
			{ "test 0 -> 1?" , true },
			{ "test 0 -> 0?" , true },

			{ "test 1 <-> 1?" , true },
			{ "test 1 <-> 0?" , false },
			{ "test 0 <-> 1?" , false },
			{ "test 0 <-> 0?" , true },

			{ "bool P = 1, bool Q = 1, test P ^ Q?" , true },
			{ "bool P = 1, bool Q = 0, test P ^ Q?" , false },
			{ "bool P = 0, bool Q = 1, test P ^ Q?" , false },
			{ "bool P = 0, bool Q = 0, test P ^ Q?" , false },

			{ "bool P = 1, bool Q = 1, bool R = 1, test P ^ Q ^ R?" , true },
			{ "bool P = 1, bool Q = 1, bool R = 0, test P ^ Q ^ R?" , false },
			{ "bool P = 1, bool Q = 0, bool R = 1, test P ^ Q ^ R?" , false },
			{ "bool P = 1, bool Q = 0, bool R = 0, test P ^ Q ^ R?" , false },
			{ "bool P = 0, bool Q = 1, bool R = 1, test P ^ Q ^ R?" , false },
			{ "bool P = 0, bool Q = 1, bool R = 0, test P ^ Q ^ R?" , false },
			{ "bool P = 0, bool Q = 0, bool R = 1, test P ^ Q ^ R?" , false },
			{ "bool P = 0, bool Q = 0, bool R = 0, test P ^ Q ^ R?" , false },

			{ "bool P = 1, bool Q = 1, test P v Q?" , true },
			{ "bool P = 1, bool Q = 0, test P v Q?" , true },
			{ "bool P = 0, bool Q = 1, test P v Q?" , true },
			{ "bool P = 0, bool Q = 0, test P v Q?" , false },

			{ "bool P = 1, bool Q = 1, bool R = 1, test P v Q v R?" , true },
			{ "bool P = 1, bool Q = 1, bool R = 0, test P v Q v R?" , true },
			{ "bool P = 1, bool Q = 0, bool R = 1, test P v Q v R?" , true },
			{ "bool P = 1, bool Q = 0, bool R = 0, test P v Q v R?" , true },
			{ "bool P = 0, bool Q = 1, bool R = 1, test P v Q v R?" , true },
			{ "bool P = 0, bool Q = 1, bool R = 0, test P v Q v R?" , true },
			{ "bool P = 0, bool Q = 0, bool R = 1, test P v Q v R?" , true },
			{ "bool P = 0, bool Q = 0, bool R = 0, test P v Q v R?" , false },

			{ "bool P = 1, bool Q = 1, test P -> Q?" , true },
			{ "bool P = 1, bool Q = 0, test P -> Q?" , false },
			{ "bool P = 0, bool Q = 1, test P -> Q?" , true },
			{ "bool P = 0, bool Q = 0, test P -> Q?" , true },

			{ "bool P = 1, bool Q = 1, bool R = 1, test P -> Q -> R?" , true },
			{ "bool P = 1, bool Q = 1, bool R = 0, test P -> Q -> R?" , false },
			{ "bool P = 1, bool Q = 0, bool R = 1, test P -> Q -> R?" , true },
			{ "bool P = 1, bool Q = 0, bool R = 0, test P -> Q -> R?" , true },
			{ "bool P = 0, bool Q = 1, bool R = 1, test P -> Q -> R?" , true },
			{ "bool P = 0, bool Q = 1, bool R = 0, test P -> Q -> R?" , true },
			{ "bool P = 0, bool Q = 0, bool R = 1, test P -> Q -> R?" , true },
			{ "bool P = 0, bool Q = 0, bool R = 0, test P -> Q -> R?" , true },

			{ "bool P = 1, bool Q = 1, test P <-> Q?" , true },
			{ "bool P = 1, bool Q = 0, test P <-> Q?" , false },
			{ "bool P = 0, bool Q = 1, test P <-> Q?" , false },
			{ "bool P = 0, bool Q = 0, test P <-> Q?" , true },

			{ "bool P = 1, bool Q = 1, bool R = 1, test P <-> Q <-> R?" , true },
			{ "bool P = 1, bool Q = 1, bool R = 0, test P <-> Q <-> R?" , false },
			{ "bool P = 1, bool Q = 0, bool R = 1, test P <-> Q <-> R?" , false },
			{ "bool P = 1, bool Q = 0, bool R = 0, test P <-> Q <-> R?" , true },
			{ "bool P = 0, bool Q = 1, bool R = 1, test P <-> Q <-> R?" , false },
			{ "bool P = 0, bool Q = 1, bool R = 0, test P <-> Q <-> R?" , true },
			{ "bool P = 0, bool Q = 0, bool R = 1, test P <-> Q <-> R?" , true },
			{ "bool P = 0, bool Q = 0, bool R = 0, test P <-> Q <-> R?" , false },

			{ "bool P = 1, test P?" , true },
			{ "bool P = 0, test P?" , false },
			{ "bool P = 1, test P'?" , false },
			{ "bool P = 0, test (P')'?" , false },
			{ "bool P = 1, test (P)?" , true },
			{ "bool P = 0, test ((P))?" , false },

			{ "bool P = 1, bool Q = 1, test (P ^ Q)' <-> (P' v Q')?" , true },
			{ "bool P = 1, bool Q = 0, test (P ^ Q)' <-> (P' v Q')?" , true },
			{ "bool P = 0, bool Q = 1, test (P ^ Q)' <-> (P' v Q')?" , true },
			{ "bool P = 0, bool Q = 0, test (P ^ Q)' <-> (P' v Q')?" , true },

			{ "bool P = 1, bool Q = 1, test (P ^ Q)' <-> (P' v Q')?" , true },
			{ "bool P = 1, bool Q = 0, test (P ^ Q)' <-> (P' v Q')?" , true },
			{ "bool P = 0, bool Q = 1, test (P ^ Q)' <-> (P' v Q')?" , true },
			{ "bool P = 0, bool Q = 0, test (P ^ Q)' <-> (P' v Q')?" , true },

			{ "bool P = 1, bool Q = 1, test (P -> Q) <-> (P' v Q)?" , true },
			{ "bool P = 1, bool Q = 0, test (P -> Q) <-> (P' v Q)?" , true },
			{ "bool P = 0, bool Q = 1, test (P -> Q) <-> (P' v Q)?" , true },
			{ "bool P = 0, bool Q = 0, test (P -> Q) <-> (P' v Q)?" , true },

			{ "bool P = 1, bool Q = 1, test P -> Q <-> P' v Q?" , true },
			{ "bool P = 1, bool Q = 0, test P -> Q <-> P' v Q?" , true },
			{ "bool P = 0, bool Q = 1, test P -> Q <-> P' v Q?" , true },
			{ "bool P = 0, bool Q = 0, test P -> Q <-> P' v Q?" , true },

			{ "bool P = 1, bool Q = 1, test P -> Q <-> Q' -> P'?" , true },
			{ "bool P = 1, bool Q = 0, test P -> Q <-> Q' -> P'?" , true },
			{ "bool P = 0, bool Q = 1, test P -> Q <-> Q' -> P'?" , true },
			{ "bool P = 0, bool Q = 0, test P -> Q <-> Q' -> P'?" , true },

			{ "Bool P = 1, Bool Q = P, Test Q?" , true },
			{ "BOOL P = 0, BOOL Q = P, TEST Q?" , false },
			{ "BoOl P = 1, bOoL Q = P', TeSt Q?" , false },
			{ "boOL P = 0, BOol Q = P', tEsT Q?" , true },

			{ "bool P = 1, bool Q = 1, bool R = P' ^ Q', test R?" , false },
			{ "bool P = 1, bool Q = 0, bool R = P' ^ Q', test R?" , false },
			{ "bool P = 0, bool Q = 1, bool R = P' ^ Q', test R?" , false },
			{ "bool P = 0, bool Q = 0, bool R = P' ^ Q', test R?" , true },

			{ "bool P = 1, test Q?" , null },
			{ "bool p = 1, test P?" , null },
			{ "bool P = 1, test p?" , null },
			{ "bool P = 1, test P V P?" , null },
			{ "bool ->->P? Q" , null },
			{ "^vTEST bool ' P )" , null },
		});
	}

	@Parameter(value=0)
	public String sentence;

	@Parameter(value=1)
	public Boolean TESTuation;

	private static model.AbstractParser PARSER;

	@BeforeClass
	public static void setup() {
		PARSER = new unit.Parser();
	}

	@Test
	public void testProgram() {
		if (TESTuation != null)
			assertEquals(
				"Valid sentence TESTuated incorrectly,",
				TESTuation,
				PARSER.evaluate(sentence.toCharArray())
			);
		else
			try {
				PARSER.evaluate(sentence.toCharArray());
				fail("Invalid sentence must throw exception,");
			}
			catch (Exception e) {}
	}
}
