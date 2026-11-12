package iuptest;

import com.sun.jna.Pointer;

import static iuptest.IUPTest.w;

public class Img
{
    public Img()
    {
        sup();
    }

    private void sup()
    {
        IUPJIm im = IUPJIm.inst;

        Pointer p = im.IupLoadImage("Documents\\bigloading.gif");
        w(p);
    }
}
