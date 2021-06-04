package Tools;

import Math.Vector3;
import Models.Vertex;
import ThirdDimension.IModel;
import ThirdDimension.Polyline3D;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ObjLoader {

    public static IModel obj2Model(File obj, boolean hasTexture) throws FileNotFoundException {
        Scanner sc = new Scanner(obj);
        ArrayList<Vector3> positions = new ArrayList<>();
        ArrayList<Vector3> normals = new ArrayList<>();
        ArrayList<Vertex> vertices = new ArrayList<>();
        ArrayList<Polyline3D> lines = new ArrayList<>();

        while (sc.hasNextLine()){
            String s = sc.nextLine();
            if(s.length() == 0) continue;
            if(s.startsWith("v ")){
                positions.add(parseString(s));
            }
            if(s.startsWith("vn")){
                normals.add(parseString(s));
            }
            if(s.charAt(0) == 'f'){
                ArrayList<ArrayList<String>> link = new ArrayList<>();
                int j = 0;
                int t = 0;
                link.add(new ArrayList<>());
                link.get(t).add("");
                for (int i = 2; i < s.length(); i++) {
                    if(s.charAt(i) == '/'){
                        j++;
                        link.get(t).add("");
                    }
                    if(Character.isDigit(s.charAt(i))){
                        String tmp = link.get(t).get(j);
                        link.get(t).set(j, tmp + s.charAt(i));
                    }
                    if(s.charAt(i) == ' '){
                        j = 0;
                        t++;
                        link.add(new ArrayList<>());
                        link.get(t).add("");
                    }
                }
                for (int i = 0; i < link.size(); i++) {
                    int posIndex = Integer.parseInt(link.get(i).get(0));
                    int normalIndex = Integer.parseInt(hasTexture ? link.get(i).get(2) : link.get(i).get(1));

                    vertices.add(new Vertex(
                            positions.get(posIndex - 1),
                            normals.get(normalIndex - 1)
                    ));
                }
            }
            if(!vertices.isEmpty()) {
                lines.add(new Polyline3D(vertices, Color.BLUE, true));
                vertices.clear();
            }
        }

        return () -> lines;
    }

    private static Vector3 parseString(String s){
        String[] dd = new String[3];
        Arrays.fill(dd, "");
        int j = -1;
        for (int i = 0; i < s.length(); i++) {
            if(Character.isDigit(s.charAt(i)) || s.charAt(i) == '-' || s.charAt(i) == '.'){
                dd[j] += s.charAt(i);
            }
            if(s.charAt(i) == ' '){
                j++;
            }
        }
        double[] d = new double[3];
        for (int i = 0; i < dd.length; i++) {
            d[i] = Double.parseDouble(dd[i]);
        }
        return new Vector3(d[0], d[1], d[2]);
    }


}
