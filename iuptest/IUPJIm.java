/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iuptest;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

/**
 *
 * @author lulz
 */
public interface IUPJIm extends Library
{
    String dll = "iupim";
    IUPJIm inst = Native.loadLibrary(dll, IUPJIm.class);

    Pointer IupLoadImage(String file_name);
    Pointer IupLoadAnimation(String file_name);

    Pointer IupImageFromImImage(Pointer image);


    Pointer IupGetImageNativeHandle(Pointer image);
}
