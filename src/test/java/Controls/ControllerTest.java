package Controls;

import com.bmcl.hero.Arena;
import com.bmcl.hero.Game;
import com.bmcl.hero.Hero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


import java.io.IOException;

public class ControllerTest extends Assertions {

    @Test

    void jump() throws IOException{
        Game game = new Game(80, 21);
        Arena arena = new Arena(80, 21, game);
        Hero hero = new Hero(5, 5);
        arena.jump();
        assert hero.moveUp(hero.getPosition());
    }
}
