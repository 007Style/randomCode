import com.chilkatsoft.*;

public class ChilkatExample {

  static {
    try {
        System.loadLibrary("chilkat");
    } catch (UnsatisfiedLinkError e) {
      System.err.println("Native code library failed to load.\n" + e);
      System.exit(1);
    }
  }

  public static void main(String argv[])
  {
    CkCrypt2 crypt = new CkCrypt2();

    boolean success;
    success = crypt.UnlockComponent("Anything for 30-day trial");
    if (success != true) {
        System.out.println("Crypt component unlock failed");
        return;
    }

    //  Use "blowfish2" to get proper results:
    crypt.put_CryptAlgorithm("blowfish2");

    //  CipherMode may be "ecb" or "cbc"
    crypt.put_CipherMode("cbc");

    //  KeyLength (in bits) may be a number between 32 and 448.
    //  128-bits is usually sufficient.  The KeyLength must be a
    //  multiple of 8.
    crypt.put_KeyLength(256);

    //  Pad with NULL bytes (PHP pads with NULL bytes)
    crypt.put_PaddingScheme(3);

    //  EncodingMode specifies the encoding of the output for
    //  encryption, and the input for decryption.
    //  It may be "hex", "url", "base64", or "quoted-printable".
    crypt.put_EncodingMode("hex");

    //  The blowfish algorithm uses a 64-bit block size,
    //  therefore the IV must be 8 bytes:
    String ivAscii;
    ivAscii = "12345678";
    crypt.SetEncodedIV(ivAscii,"ascii");

    //  For 256-bit encryption, the key is 32 bytes:
    String keyAscii;
    keyAscii = "1234567890123456ABCDEFGHIJKLMNOP";
    crypt.SetEncodedKey(keyAscii,"ascii");

    String plainText;
    plainText = "The quick brown fox jumped over the lazy dog";

    String cipherText;
    cipherText = crypt.encryptStringENC(plainText);
    System.out.println(cipherText);
    //  Output should be:
    //  276855ca6c0d60f7d9708210440c1072e05d078e733b34b4198d609dc2fcc2f0c30926cdef3b6d52baf6e345aa03f83e

    //  Do 128-bit Blowfish encryption:
    crypt.put_KeyLength(128);
    keyAscii = "1234567890123456";
    crypt.SetEncodedKey(keyAscii,"ascii");

    cipherText = crypt.encryptStringENC(plainText);
    System.out.println(cipherText);
    //  Output should be:
    //  d2b5abb73208aea3790621d028afcc74d8dd65fb9ea8e666444a72523f5ecca60df79a424e2c714fa6efbafcc40bdca0

  }
}
