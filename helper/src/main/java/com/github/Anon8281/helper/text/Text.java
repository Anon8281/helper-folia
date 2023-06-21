/*
 * This file is part of helper, licensed under the MIT License.
 *
 *  Copyright (c) lucko (Luck) <luck@lucko.me>
 *  Copyright (c) contributors
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.github.Anon8281.helper.text;

import me.lucko.helper.text.Component;
import me.lucko.helper.text.TextComponent;
import me.lucko.helper.text.adapter.bukkit.TextAdapter;
import me.lucko.helper.text.serializer.ComponentSerializers;

import org.bukkit.command.CommandSender;

import java.util.stream.Stream;

/**
 * Utilities for working with {@link Component}s and formatted text strings.
 *
 * @deprecated Use {@link com.github.Anon8281.helper.text3.Text}
 */
@Deprecated
public final class Text {

    public static final char SECTION_CHAR = com.github.Anon8281.helper.text3.Text.SECTION_CHAR;
    public static final char AMPERSAND_CHAR = com.github.Anon8281.helper.text3.Text.AMPERSAND_CHAR;

    public static String joinNewline(String... strings) {
        return com.github.Anon8281.helper.text3.Text.joinNewline(strings);
    }

    public static String joinNewline(Stream<String> strings) {
        return com.github.Anon8281.helper.text3.Text.joinNewline(strings);
    }

    public static TextComponent fromLegacy(String input, char character) {
        return ComponentSerializers.LEGACY.deserialize(input, character);
    }

    public static TextComponent fromLegacy(String input) {
        return ComponentSerializers.LEGACY.deserialize(input);
    }

    public static String toLegacy(Component component, char character) {
        return ComponentSerializers.LEGACY.serialize(component, character);
    }

    public static String toLegacy(Component component) {
        return ComponentSerializers.LEGACY.serialize(component);
    }

    public static void sendMessage(CommandSender sender, Component message) {
        TextAdapter.sendComponent(sender, message);
    }

    public static void sendMessage(Iterable<CommandSender> senders, Component message) {
        TextAdapter.sendComponent(senders, message);
    }

    public static String colorize(String s) {
        return com.github.Anon8281.helper.text3.Text.colorize(s);
    }

    public static String decolorize(String s) {
        return com.github.Anon8281.helper.text3.Text.decolorize(s);
    }

    public static String translateAlternateColorCodes(char from, char to, String textToTranslate) {
        return com.github.Anon8281.helper.text3.Text.translateAlternateColorCodes(from, to, textToTranslate);
    }

    private Text() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

}
