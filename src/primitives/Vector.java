package primitives;

public class Vector {
    private Point3D head;

    //region ctors
    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        var p = new Point3D(x,y,z);
        if(p.equals(Point3D.ZERO))
            throw new IllegalArgumentException("Error! Vector 0 is not allowed!");
        else
            head = p;
    }
    public Vector(double x, double y, double z) {
        var p = new Point3D(x,y,z);
        if(p.equals(Point3D.ZERO))
            throw new IllegalArgumentException("Error! Vector 0 is not allowed!");
        else
            head = p;
    }
    public Vector(Point3D p) {
        head = p;
    }
    //endregion
    // region Methods
    public double lengthSquared() {
        return head.x.coord*head.x.coord + head.y.coord*head.y.coord + head.z.coord*head.z.coord;
    }

    public double length() {
        double lenSq = this.lengthSquared();
        return Math.sqrt(lenSq);
    }

    public double dotProduct(Vector that) {
        return this.head.x.coord * that.head.x.coord + this.head.y.coord * that.head.y.coord + this.head.z.coord * that.head.z.coord;
    }

    public Vector crossProduct(Vector that) {
        var x = this.head.y.coord * that.head.z.coord - this.head.z.coord * that.head.y.coord;
        var y = this.head.z.coord * that.head.x.coord - this.head.x.coord * that.head.z.coord;
        var z = this.head.x.coord * that.head.y.coord - this.head.y.coord * that.head.x.coord;
        return new Vector(x,y,z);
    }

    public Point3D getHead() {
        return head;
    }

    public Vector normalize() {
        var normalized = this.normalized();
        this.head = normalized.head;
        return this;
    }

    public Vector normalized() {
        var len = this.length();
        return new Vector(this.head.x.coord / len, this.head.y.coord / len, this.head.z.coord / len);
    }
    // endregion
    // region Overrides
    @Override
    public String toString() {
        return 'V' + head.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Vector)) return false;
        Vector vector = (Vector) o;
        return this.getHead().equals(vector.getHead());
    }
    // endregion
}
