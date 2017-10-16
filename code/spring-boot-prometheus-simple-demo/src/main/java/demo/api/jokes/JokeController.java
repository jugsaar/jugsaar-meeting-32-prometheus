package demo.api.jokes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/jokes")
class JokeController {

  private static final String[] JOKES = {

    "\"Knock, knock.\"\n\n" +
      "\"Who’s there?\"\n\n" +
      "\"very long pause…\"\n\n" +
      " \n\n" +
      "Java."
    , "\t\n" +
    "If you put a million monkeys at a million keyboards, one of them will eventually write a Java program.\n\nThe rest of them will write Perl programs."
    , "Two threads walk into a bar. The barkeeper looks up and yells, \"hey, I want don't any conditions race like time last!\""
  };

  @GetMapping("/random")
  Object greet() {
    return Collections.singletonMap("joke", JOKES[(int) (Math.random() * JOKES.length)]);
  }
}