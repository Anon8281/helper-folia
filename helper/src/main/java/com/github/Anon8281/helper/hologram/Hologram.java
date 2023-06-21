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

package com.github.Anon8281.helper.hologram;

import com.google.gson.JsonElement;

import com.github.Anon8281.helper.Services;
import com.github.Anon8281.helper.gson.GsonSerializable;
import com.github.Anon8281.helper.serialize.Position;

import java.util.List;

import javax.annotation.Nonnull;

/**
 * A simple hologram utility.
 */
public interface Hologram extends BaseHologram, GsonSerializable {

    /**
     * Creates and returns a new hologram
     *
     * <p>Note: the hologram will not be spawned automatically.</p>
     *
     * @param position the position of the hologram
     * @param lines the initial lines to display
     * @return the new hologram.
     */
    @Nonnull
    static Hologram create(@Nonnull Position position, @Nonnull List<String> lines) {
        return Services.load(HologramFactory.class).newHologram(position, lines);
    }

    static Hologram deserialize(JsonElement element) {
        return Services.load(HologramFactory.class).deserialize(element);
    }

    /**
     * Updates the lines displayed by this hologram
     *
     * <p>This method does not refresh the actual hologram display. {@link #spawn()} must be called for these changes
     * to apply.</p>
     *
     * @param lines the new lines
     */
    void updateLines(@Nonnull List<String> lines);

}
