package com.platformer.game;

import java.util.ArrayList;
import java.util.List;

public class Quad {

    public float[][] points;

    public class Point {
        private float x;
        private float y;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public float getX(){return x;}

        public float getY(){return y;}
    }

    public class Region {
        private float x1;
        private float y1;
        private float x2;
        private float y2;

        public Region(float x1, float y1, float x2, float y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public boolean containsPoint(Point point) {
            return point.getX() >= this.x1
                    && point.getX() < this.x2
                    && point.getY() >= this.y1
                    && point.getY() < this.y2;
        }

        public boolean doesOverlap(Region testRegion) {
            if (testRegion.getX2() < this.getX1()) {
                return false;
            }
            if (testRegion.getX1() > this.getX2()) {
                return false;
            }
            if (testRegion.getY1() > this.getY2()) {
                return false;
            }
            if (testRegion.getY2() < this.getY1()) {
                return false;
            }
            return true;
        }

        public Region getQuadrant(int quadrantIndex) {
            float quadrantWidth = (this.x2 - this.x1) / 2;
            float quadrantHeight = (this.y2 - this.y1) / 2;

            // 0=SW, 1=NW, 2=NE, 3=SE
            switch (quadrantIndex) {
                case 0:
                    return new Region(x1, y1, x1 + quadrantWidth, y1 + quadrantHeight);
                case 1:
                    return new Region(x1, y1 + quadrantHeight, x1 + quadrantWidth, y2);
                case 2:
                    return new Region(x1 + quadrantWidth, y1 + quadrantHeight, x2, y2);
                case 3:
                    return new Region(x1 + quadrantWidth, y1, x2, y1 + quadrantHeight);
            }
            return null;
        }

        // getters & toString()

        public float getX1(){return x1;}

        public float getY1(){return y1;}

        public float getX2(){return x2;}

        public float getY2(){return y2;}
    }

    public class QuadTree {
        private static final int MAX_POINTS = 3;
        private Region area;
        private List<Point> points = new ArrayList<>();
        private List<QuadTree> quadTrees = new ArrayList<>();

        public QuadTree(Region area) {
            this.area = area;
        }

        private boolean addPointToOneQuadrant(Point point) {
            boolean isPointAdded;
            for (int i = 0; i < 4; i++) {
                isPointAdded = this.quadTrees.get(i)
                        .addPoint(point);
                if (isPointAdded)
                    return true;
            }
            return false;
        }

        private boolean addPoint(Point point) {
            if (this.area.containsPoint(point)) {
                if (this.points.size() < MAX_POINTS) {
                    this.points.add(point);
                    return true;
                } else {
                    if (this.quadTrees.size() == 0) {
                        createQuadrants();
                    }
                    return addPointToOneQuadrant(point);
                }
            }
            return false;
        }

        private void createQuadrants() {
            Region region;
            for (int i = 0; i < 4; i++) {
                region = this.area.getQuadrant(i);
                quadTrees.add(new QuadTree(region));
            }
        }

        public List<Point> search(Region searchRegion, List<Point> matches) {
            if (matches == null) {
                matches = new ArrayList<Point>();
            }
            if (!this.area.doesOverlap(searchRegion)) {
                return matches;
            } else {
                for (Point point : points) {
                    if (searchRegion.containsPoint(point)) {
                        matches.add(point);
                    }
                }
                if (this.quadTrees.size() > 0) {
                    for (int i = 0; i < 4; i++) {
                        quadTrees.get(i)
                                .search(searchRegion, matches);
                    }
                }
            }
            return matches;
        }
    }


    public void DefineQuadTree(float[][] _points){
        points = _points;
    }

    public boolean SearchRegion(float x1, float y1, float x2,float y2){
        Region area = new Region(-400, -400, 400, 400);
        QuadTree quadTree = new QuadTree(area);

        for (int i = 0; i < points.length; i++) {
            Point point = new Point(points[i][0], points[i][1]);
            quadTree.addPoint(point);

        }

        //System.out.println(x2 + " " +y2 + " " +x1 + " " +y1);
        Region searchArea = new Region(x2,y2,x1,y1);

        List<Point> result = quadTree.search(searchArea, null);



        for(int i = 0; i < result.size() ; i++){
            System.out.println(result.get(i).x + " " + result.get(i).y);
        }


        if(result.size() > 0) {
            return true;
        } else {
            return false;
        }

    }
}
