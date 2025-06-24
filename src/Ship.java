public class Ship {
    private int size;
    private char letter;
    private char direction;
    private Coordinate coord;

    public Ship (char let, char dir, Coordinate crd){
        letter = let;
        direction = dir;
        coord = crd;

        switch (let){
            case 'A': size = 5;break;
            case 'B': size = 4;break;
            case 'S':
            case 'D':size = 3;break;
            case 'P':size = 2;break;
            default: throw new IllegalArgumentException("Letra invalida");

        }
    }

    public int getSize(){
        return size;
    }

    public char getLetter(){
        return letter;
    }

    public char getDirection() {
        return direction;
    }

    public Coordinate getCoord() {
        return coord;
    }
}
