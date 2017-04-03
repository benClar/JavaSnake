import java.rmi.UnexpectedException;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by benjaminclarke on 03/04/2017.
 */
public class Snake {

    private Coordinates head;
    private LinkedList<Coordinates> trail;
    private int size;
    private Direction direction;

    public Snake(){
        this.trail = new LinkedList<>();
        this.trail.add(new Coordinates(Game.WIDTH/2, Game.HEIGHT/2));
        this.size = 1;
        this.direction = Direction.RIGHT;
    }

    public Coordinates getPosition(){
        return this.trail.getFirst();
    }

    public void setPosition(Coordinates position){
        this.trail.addFirst(position);
    }

    public void Move() throws UnexpectedException {
        int newY, newX;

        switch (this.direction){
            case UP:
                newY = incrementPosition(this.getPosition().y, Game.HEIGHT);
                newX = this.getPosition().x;
                break;
            case DOWN:
                newY = decrementPosition(this.getPosition().y, Game.HEIGHT);
                newX = this.getPosition().x;
                break;
            case RIGHT:
                newX = incrementPosition(this.getPosition().x, Game.WIDTH);
                newY = this.getPosition().y;
                break;
            case LEFT:
                newX = decrementPosition(this.getPosition().x, Game.WIDTH);
                newY = this.getPosition().y;
                break;
            default:
                throw new UnexpectedException("Unexpected Direction");
        }
        this.setPosition(new Coordinates(newX, newY));
    }

    private int incrementPosition(int coord, int boundary){
        if (coord == boundary + 1){
            return 0;
        }
        return coord + 1;
    }

    private int decrementPosition(int coord, int boundary){
        if (coord == 0){
            return boundary - 1;
        }
        return coord - 1;
    }

}
