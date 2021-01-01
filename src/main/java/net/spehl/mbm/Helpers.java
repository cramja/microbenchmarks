package net.spehl.mbm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public final class Helpers {
  private static final Random random = new Random();

  public static String randomString(int len) {
    char[] chars = "abcdefghijklmnopqrstuvwxyz _-1234567890".toCharArray();
    char[] s = new char[len];
    for (int i = 0; i < len; i++) {
      s[i] = chars[random.nextInt(chars.length)];
    }
    return new String(s);
  }

  public static Map<String, String> randomMap() {
    HashMap<String,String> m = new HashMap<>();
    for (int i = 0; i < 10; i++) {
      m.put(randomString(32), randomString(32));
    }
    return m;
  }

  public static List<String> randomList() {
    List<String> m = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      m.add(randomString(32));
    }
    return m;
  }

}
