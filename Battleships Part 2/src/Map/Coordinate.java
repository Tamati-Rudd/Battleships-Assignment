package Map;

import java.util.Objects;

/**
 * This class defines a single coordinate, which is a single spot on a battleships map
 * @author Tamati Rudd 18045626
 */
public class Coordinate implements State {
    private char xCoord;
    private int yCoord;
    private Status coordState;
    private Boolean hasShip;
    
    public Coordinate(char xValue, int yValue) {
        this.xCoord = xValue;
        this.yCoord = yValue;
        this.coordState = Status.HIDDEN;
        this.hasShip = false;
    }
    
    public char getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public Status getCoordState() {
        return coordState;
    }

    public void setCoordState(Status coordState) {
        this.coordState = coordState;
    }

    @Override
    public Boolean checkForShip() {
        return this.hasShip;
    }

    @Override
    public void shipPlaced() {
        this.hasShip = true;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.xCoord;
        hash = 29 * hash + this.yCoord;
        hash = 29 * hash + Objects.hashCode(this.coordState);
        hash = 29 * hash + Objects.hashCode(this.hasShip);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordinate other = (Coordinate) obj;
        if (this.xCoord != other.xCoord) {
            return false;
        }     
        if (this.yCoord != other.yCoord) {
            return false;
        }
        return true;
    }
}
