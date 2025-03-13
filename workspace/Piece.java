
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