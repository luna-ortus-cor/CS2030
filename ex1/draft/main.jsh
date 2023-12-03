double epsilon = 1E-15;
double radius = 1.0;
double offset = Math.PI / 2;

double distanceBetween(Point p, Point q) {
    // double dx = p.xdist(q);
    // double dy = p.ydist(q);
    // return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    return p.distBetween(q);
}

boolean circleContainsPoint(Circle c, Point p) {
    return distanceBetween(c.getCentre(), p) < c.getRadius() + epsilon;
}

int findMaxDiscCoverage(ImList<Point> points) {
    int maxDiscCoverage = 0;
    int numOfPoints = points.size();

    // for (int i = 0; i < numOfPoints - 1; i++) {
        // for (int j = i + 1; j < numOfPoints; j++) {
    for (int i = 0; i < numOfPoints; i++) {
        for (int j = 0; j < numOfPoints; j++) {
            Point p = points.get(i);
            Point q = points.get(j);
            double distPQ = distanceBetween(p, q);
            if (distPQ < 2.0 + epsilon && distPQ > 0) {
                // Point midpoint = p.midPoint(q);
                // double cp = Math.sqrt(1.0 - Math.pow(distanceBetween(midpoint, p), 2));
                // double theta = p.angleTo(q);
                // Circle c = new Circle(
                //         new Point(p.x + cp * Math.cos(theta + Math.PI / 2),
                //             p.y + cp * Math.sin(theta + Math.PI / 2)), 
                //         1.0);
                Circle c = createUnitCircle(p, q);
                // System.out.println(c);
                int coverage = 0;
                for (Point point : points) {
                    if (circleContainsPoint(c, point)) {
                        coverage = coverage + 1;
                    }
                }
                if (coverage > maxDiscCoverage) {
                    maxDiscCoverage = coverage;
                }
            }
        }
    }
    return maxDiscCoverage;
}

Circle createUnitCircle(Point p,Point q){
    // double s = ((2 * radius) + distanceBetween(p,q)) / 2;
    // double area = Math.sqrt(s * (s - radius) * (s - radius) * (s - distanceBetween(p,q)));
    // double dist = 2*area/distanceBetween(p,q);
    // double angle = (p.angleTo(q) >= 0 && p.angleTo(q) < Math.PI ? 
    //     p.angleTo(q) + offset : 
    //     p.angleTo(q) - offset);
    // return new Circle(p.midPoint(q).moveTo(angle,dist),radius);
    Point midpoint = p.midPoint(q);
    double cp = Math.sqrt(radius - Math.pow(distanceBetween(midpoint, p), 2));
    double theta = p.angleTo(q);
    return new Circle(midpoint.moveTo(theta + offset, cp), radius);
}
