package me.ashydev.nightitems.util;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;

@Getter
public class Color {
    private final byte[] bytes = new byte[4];

    public Color(int r, int g, int b, int a) {
        if (r > 255 || r < 0) throw new IllegalArgumentException("r must be between 0 and 255");
        if (g > 255 || g < 0) throw new IllegalArgumentException("g must be between 0 and 255");
        if (b > 255 || b < 0) throw new IllegalArgumentException("b must be between 0 and 255");
        if (a > 255 || a < 0) throw new IllegalArgumentException("a must be between 0 and 255");

        bytes[0] = (byte) r;
        bytes[1] = (byte) g;
        bytes[2] = (byte) b;
        bytes[3] = (byte) a;
    }

    public Color(int r, int g, int b) {
        this(r, g, b, 255);
    }

    public Color(byte r, byte g, byte b, byte a) {
        bytes[0] = r;
        bytes[1] = g;
        bytes[2] = b;
        bytes[3] = a;
    }


    public Color(String hex) {
        if (hex.startsWith("#")) hex = hex.substring(1);
        if (hex.length() != 8) throw new IllegalArgumentException("hex must be 8 characters long");

        for (int i = 0; i < 4; i++) {
            String s = hex.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(s, 16);
        }
    }

    public String getHex() {
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            String s = Integer.toHexString(b & 0xFF);
            if (s.length() == 1) hex.append('0');
            hex.append(s);
        }
        return hex.toString();
    }

    public String getHex(String prefix) {
        StringBuilder hex = new StringBuilder();
        hex.append(prefix);
        for (byte b : bytes) {
            String s = Integer.toHexString(b & 0xFF);
            if (s.length() == 1) hex.append('0');
            hex.append(s);
        }
        return hex.toString();
    }
    public static Color fromHex(String hex) {
        return new Color(hex);
    }

    public static Color fromRGB(int r, int g, int b) {
        return new Color(r, g, b);
    }

    public static Color fromRGBA(int r, int g, int b, int a) {
        return new Color(r, g, b, a);
    }

    public static Color fromBytes(byte r, byte g, byte b, byte a) {
        return new Color(r, g, b, a);
    }

    public static Color fromBytes(byte r, byte g, byte b) {
        return new Color(r, g, b, (byte) 255);
    }

    public static Component colorize(String text) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(text).style(style -> style.decoration(TextDecoration.ITALIC, false));
    }

    @SuppressWarnings("deprecation")
    public static String translate(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
