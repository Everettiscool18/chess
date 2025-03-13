
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Piece {
    private final boolean color;
    private BufferedImage img;
    
    public Piece(boolean isWhite, String img_file) {
        this.color = isWhite;
        
        try {
            if (this.img == null) {
              this.img = ImageIO.read(getClass().getResource(img_file));
            }
          } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
          }
    }
    
    

    
    public boolean getColor() {
        return color;
    }
    
    public Image getImage() {
        return img;
    }
    
    public void draw(Graphics g, Square currentSquare) {
        int x = currentSquare.getX();
        int y = currentSquare.getY();
        
        g.drawImage(this.img, x, y, null);
    }
    
    
    // PreCondition: Requires a square grid structure and a Square instance representing the current piece's initial position.
    // PostCondition: Returns an ArrayList of Square instances that the piece controls, can capture, or is defending.
    public ArrayList<Square> getControlledSquares(Square[][] b, Square start) {
      ArrayList<Square> moves = new ArrayList<Square>();
      for (int colRow = start.getRow(); colRow < 8; colRow++) {
          if (start.getRow() != colRow) {
              Square targetSquare = b[colRow][start.getCol()];
              if (targetSquare.getOccupyingPiece() == null) {
                  moves.add(targetSquare);
              } else {
                  moves.add(targetSquare);
                  if (targetSquare.getOccupyingPiece().getColor() != start.getOccupyingPiece().getColor()) {
                      break;
                  }
              }
          }
      }
  
      for (int colRow = start.getRow(); colRow >= 0; colRow--) {
          if (start.getRow() != colRow) {
              Square targetSquare = b[colRow][start.getCol()];
              if (targetSquare.getOccupyingPiece() == null) {
                  moves.add(targetSquare);
              } else {
                  moves.add(targetSquare);
                  if (targetSquare.getOccupyingPiece().getColor() != start.getOccupyingPiece().getColor()) {
                      break;
                  }
              }
          }
      }
  
      for (int rowCol = start.getCol(); rowCol < 8; rowCol++) {
          if (start.getCol() != rowCol) {
              Square targetSquare = b[start.getRow()][rowCol];
              if (targetSquare.getOccupyingPiece() == null) {
                  moves.add(targetSquare);
              } else {
                  moves.add(targetSquare);
                  if (targetSquare.getOccupyingPiece().getColor() != start.getOccupyingPiece().getColor()) {
                      break;
                  }
              }
          }
      }
  
      for (int rowCol = start.getCol(); rowCol >= 0; rowCol--) {
          if (start.getCol() != rowCol) {
              Square targetSquare = b[start.getRow()][rowCol];
              if (targetSquare.getOccupyingPiece() == null) {
                  moves.add(targetSquare);
              } else {
                  moves.add(targetSquare);
                  if (targetSquare.getOccupyingPiece().getColor() != start.getOccupyingPiece().getColor()) {
                      break;
                  }
              }
          }
      }
  
      return moves;
  }
  

   // PreCondition: Requires a board instance and a Square instance representing the current piece's initial position.
  // PostCondition: Returns an ArrayList of Square instances representing valid moves for the piece.

public ArrayList<Square> getLegalMoves(Board b, Square start) {
  ArrayList<Square> moves = new ArrayList<Square>();
  System.out.println("this was called");

  // Vertical Movement
  for (int colRow = start.getRow(); colRow < 8; colRow++) {
      if (start.getRow() != colRow) {
          Square targetSquare = b.getSquareArray()[colRow][start.getCol()];
          if (targetSquare.getOccupyingPiece() == null) {
              moves.add(targetSquare);
          } else {
              if (targetSquare.getOccupyingPiece().getColor() != start.getOccupyingPiece().getColor()) {
                  moves.add(targetSquare);
              }
              break;
          }
      }
  }

  for (int colRow = start.getRow(); colRow >= 0; colRow--) {
      if (start.getRow() != colRow) {
          Square targetSquare = b.getSquareArray()[colRow][start.getCol()];
          if (targetSquare.getOccupyingPiece() == null) {
              moves.add(targetSquare);
          } else {
              if (targetSquare.getOccupyingPiece().getColor() != start.getOccupyingPiece().getColor()) {
                  moves.add(targetSquare);
              }
              break;
          }
      }
  }

  // Horizontal Movement
  for (int rowCol = start.getCol(); rowCol < 8; rowCol++) {
      if (start.getCol() != rowCol) {
          Square targetSquare = b.getSquareArray()[start.getRow()][rowCol];
          if (targetSquare.getOccupyingPiece() == null) {
              moves.add(targetSquare);
          } else {
              if (targetSquare.getOccupyingPiece().getColor() != start.getOccupyingPiece().getColor()) {
                  moves.add(targetSquare);
              }
              break;
          }
      }
  }

  for (int rowCol = start.getCol(); rowCol >= 0; rowCol--) {
      if (start.getCol() != rowCol) {
          Square targetSquare = b.getSquareArray()[start.getRow()][rowCol];
          if (targetSquare.getOccupyingPiece() == null) {
              moves.add(targetSquare);
          } else {
              if (targetSquare.getOccupyingPiece().getColor() != start.getOccupyingPiece().getColor()) {
                  moves.add(targetSquare);
              }
              break;
          }
      }
  }

  return moves;
}
}

On Thu, Mar 13, 2025 at 9:00 AM Chun, Everett <10018341@mcpsmd.net> wrote:
Square.java




import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.*;


//Please read the following class carefully! It represents a single chess board square and is what you'll be using
//to represent the chessboard.
@SuppressWarnings("serial")
public class Square extends JComponent {
    //a reference back to the board that stores this square.
    private Board b;
    
    //true for white, false for black.
    private final boolean color;
    //if there's a piece on the square this stores it. If there isn't this stores null.
    private Piece occupyingPiece;
    
    //if desired you can use this to retain the piece where it is but make it invisible to the user. 
    //True means to display the piece. This property will be switched to false when we are dragging a piece around while choosing our next move.
    private boolean dispPiece;
    
    //the coordinates of the square.
    private int row;
    private int col;
    
    
    public Square(Board b, boolean isWhite, int row, int col) {
        
        this.b = b;
        this.color = isWhite;
        this.dispPiece = true;
        this.row = row;
        this.col = col;
        
        
        this.setBorder(BorderFactory.createEmptyBorder());
    }
    
    public boolean getColor() {
        return this.color;
    }
    
    public Piece getOccupyingPiece() {
        return occupyingPiece;
    }
    
    public boolean isOccupied() {
        return (this.occupyingPiece != null);
    }
    
    public int getRow() {
        return this.row;
    }
    
    public int getCol() {
        return this.col;
    }
    
    public void setDisplay(boolean v) {
        this.dispPiece = v;
    }
    
    public void put(Piece p) {
        this.occupyingPiece = p;
    }
    
    public Piece removePiece() {
        Piece p = this.occupyingPiece;
        this.occupyingPiece = null;
        return p;
    }

    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (this.color) {
            g.setColor(new Color(221,192,127));
        } else {
            g.setColor(new Color(101,67,33));
        }
        
        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        
        if(occupyingPiece != null && dispPiece) {
            occupyingPiece.draw(g, this);
        }
    }
    
    
}
