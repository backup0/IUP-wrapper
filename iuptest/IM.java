package iuptest;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;

import java.util.Arrays;
import java.util.List;

import static iuptest.IUPTest.*;

public interface IM extends Library
{
    String dll = "im";
    IM inst = Native.loadLibrary(dll, IM.class);

    Pointer imFileNew(String file_name, String format, IntByReference error);
    Pointer imFileNew(ImBinMemoryFileName file_name, String format, IntByReference error);
    Pointer imFileOpen(ImBinMemoryFileName file_name, IntByReference error);
    
    // doesn't work
    //void imFileGetInfo(Pointer ifile, byte[] format, byte[] compression, IntByReference image_count);
    //void imFileGetInfo(Pointer ifile, String format, String compression, IntByReference image_count);
    void imFileGetInfo(Pointer ifile, Pointer format, Pointer compression, IntByReference image_count);
    void imFileGetInfo(Pointer ifile, IntByReference format, IntByReference compression, IntByReference image_count);
    void imFileGetInfo(Pointer ifile, StringByRef format, StringByRef compression, IntByReference image_count);
    //void imFileGetInfo(Pointer ifile, char[] format, char[] compression, IntByReference image_count);
    //void imFileGetInfo(Pointer ifile, char[] format, char[] compression, IntByReference image_count);
    
    int imFileReadImageInfo(Pointer ifile, int index, IntByReference width, IntByReference height,
                            IntByReference file_color_mode, IntByReference file_data_type);

    //Pointer imFileOpen(String file_name, IntByReference error);
    // imFile* imFileOpen(const char* file_name, int *error);
    Pointer imFileOpen(String file_name, IntByReference error);

    Pointer imFileImageLoad(String file_name, int index, IntByReference error);
    Pointer imFileLoadBitmap(Pointer ifile, int index, IntByReference error);
    void imFileClose(Pointer file);
    int imImageIsBitmap(Pointer image);
    Pointer imFileImageLoadBitmap(String  file_name, int index, IntByReference error);

    int imBinFileSetCurrentModule(int pModule);

    public class ImBinMemoryFileName extends Structure
    {
        public Pointer buffer;
        public int size;
        public float reallocate;

        @Override
        protected List<String> getFieldOrder()
        {
            return Arrays.asList("buffer", "size", "reallocate");
        }
    }
}
