package updatedHouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;


class Furniture {
    private int x;
    private int y;
    private String name;

    public Furniture(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }
}


class Room {
    private int width;
    private int height;
    private Color color;
    private String name;

    public Room(int width, int height, Color color, String name) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    private List<Furniture> furnitureList = new ArrayList<>();

    public List<Furniture> getFurnitureList() {
        return furnitureList;
    }

    public void addFurniture(Furniture furniture) {
        furnitureList.add(furniture);
    }
}
class House {
    private Room[] rooms;

    public House(int numRooms) {
        rooms = new Room[numRooms];
    }

    public void setRoom(int index, int width, int height, Color color, String name) {
        rooms[index] = new Room(width, height, color, name);
    }

    public Room getRoom(int index) {
        return rooms[index];
    }

    public int getRoomCount() {
        return rooms.length;
    }
}

class HousePanel extends JPanel {
    private House house;
    private int roomHeight;

    public HousePanel(House house, int roomHeight) {
        this.house = house;
        this.roomHeight = roomHeight;

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                int roomIndex = findRoomIndex(mouseX, mouseY);

                if (roomIndex >= 0 && roomIndex < house.getRoomCount()) {
                    navigateToRoom(roomIndex);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int startX = 50;
        int startY = 50;
        int spacing = 0;

        for (int i = 0; i < house.getRoomCount(); i++) {
            Room room = house.getRoom(i);
            g.setColor(room.getColor());

            g.fillRect(startX, startY, room.getWidth(), roomHeight);
            g.setColor(Color.BLACK);
            g.drawRect(startX, startY, room.getWidth(), roomHeight);
            g.drawString(room.getName(), startX+10, startY +10);
            
            

            startX += room.getWidth() + spacing;
            if ((i + 1) % 3 == 0) {
                startY += roomHeight + spacing;
                startX = 50;
            }
        }
    }

    private int findRoomIndex(int mouseX, int mouseY) {
        int startX = 50;
        int startY = 50;
        int spacing = 30;

        for (int i = 0; i < house.getRoomCount(); i++) {
            int endX = startX + house.getRoom(i).getWidth();
            int endY = startY + roomHeight;

            if (mouseX >= startX && mouseX <= endX && mouseY >= startY && mouseY <= endY) {
                return i;
            }

            startX += house.getRoom(i).getWidth() + spacing;
            if ((i + 1) % 3 == 0) {
                startY += roomHeight + spacing;
                startX = 50;
            }
        }
        return -1; // No room found at the mouse click position
    }

    public void navigateToRoom(int roomIndex) {
        Room room = house.getRoom(roomIndex);
        JOptionPane.showMessageDialog(this, "Navigating to Room " + roomIndex + " (Size: " + room.getWidth() + "x" + roomHeight + ")");
    }
    public void setColorForRoom(int roomIndex) {
        Color color = JColorChooser.showDialog(this, "Choose Room Color", Color.WHITE);
        if (color != null) {
            Room room = house.getRoom(roomIndex);
            room.setColor(color);
            repaint(); // Repaint to update the color
        }
    }

    public void setNameForRoom(int roomIndex) {
        String name = JOptionPane.showInputDialog("Enter the new name for the room:");
        if (name != null && !name.isEmpty()) {
            Room room = house.getRoom(roomIndex);
            room.setName(name);
            repaint(); // Repaint to update the name
        }
    }
    
    public void addFurnitureToRoom(int roomIndex, int x, int y, String furnitureName) {
        Room room = house.getRoom(roomIndex);
        room.addFurniture(new Furniture(x, y, furnitureName));
        repaint();
    }
}

class HouseFrame extends JFrame {
	
	
    public HouseFrame(House house, int roomHeight) {
        HousePanel housePanel = new HousePanel(house, roomHeight);
        add(housePanel);

        setTitle("Customized House Design");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton setColorButton = new JButton("Set Color");
        setColorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter the room index:");
                int roomIndex = Integer.parseInt(input);
             housePanel.setColorForRoom(roomIndex);
            }
        });
        add(setColorButton, BorderLayout.NORTH);

        JButton setNameButton = new JButton("Set Name");
        setNameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter the room index:");
                int roomIndex = Integer.parseInt(input);
              housePanel.setNameForRoom(roomIndex);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(setNameButton);
        add(buttonPanel, BorderLayout.WEST);
        

        JButton addFurnitureButton = new JButton("Add Furniture");
        addFurnitureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String roomInput = JOptionPane.showInputDialog("Enter the room index:");
                int roomIndex = Integer.parseInt(roomInput);

                String xInput = JOptionPane.showInputDialog("Enter the x coordinate:");
                int x = Integer.parseInt(xInput);

                String yInput = JOptionPane.showInputDialog("Enter the y coordinate:");
                int y = Integer.parseInt(yInput);

                String furnitureName = JOptionPane.showInputDialog("Enter the furniture name:");

                housePanel.addFurnitureToRoom(roomIndex, x, y, furnitureName);
            }
        });
        
        
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(setNameButton);
        buttonPanel.add(addFurnitureButton);
        add(buttonPanel, BorderLayout.WEST);
    }
    
    
}

public class Main {
    public static void main(String[] args) {
        String userInput = JOptionPane.showInputDialog("Enter number of rooms:");

        if (userInput == null) {
            System.out.println("User canceled input.");
            System.exit(0);
        }

        int number = Integer.parseInt(userInput);
        House house = new House(number);
        int roomHeight = 200;

        for (int i = 0; i < number; i++) {
            String roomW = JOptionPane.showInputDialog("Enter width of room " + (i + 1));
            int w = Integer.parseInt(roomW);
            String room = "Room " + i;
            house.setRoom(i, w, roomHeight, Color.WHITE, room);
        }

        HouseFrame frame = new HouseFrame(house, roomHeight);
        frame.setVisible(true);
    }
}
