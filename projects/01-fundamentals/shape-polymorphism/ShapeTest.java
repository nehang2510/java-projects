abstract class shape{
abstract void area();
}
class triangle extends shape{
int base=10;
int height=5;

void area(){
System.out.println("area of triangle="+(0.5*base*height));
}
}
class rectangle extends shape{
int length=8;
int width=4;

void area(){
System.out.println("area of rectangle="+(length*width));
}
}
class circle extends shape{
int radius=7;

void area(){
System.out.println("area of circle="+(3.14*radius*radius));
}
}
class ShapeTest {
public static void main(String args[])
{
shape s;
s=new triangle();
s.area();

s=new rectangle();
s.area();

s=new circle();
s.area();
}
}