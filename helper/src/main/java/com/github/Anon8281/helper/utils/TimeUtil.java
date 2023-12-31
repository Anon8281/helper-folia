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

package com.github.Anon8281.helper.utils;

import com.github.Anon8281.helper.time.DurationFormatter;
import com.github.Anon8281.helper.time.Time;

import java.time.Duration;

import javax.annotation.Nonnull;

/**
 * Time utilities.
 *
 * @deprecated use {@link Time}
 */
@Deprecated
public final class TimeUtil {

    public static long now() {
        return Time.nowMillis();
    }

    public static long nowUnix() {
        return Time.nowSeconds();
    }

    @Nonnull
    public static String toShortForm(long seconds) {
        return DurationFormatter.CONCISE.format(Duration.ofSeconds(seconds));
    }

    @Nonnull
    public static String toLongForm(long seconds) {
        return DurationFormatter.LONG.format(Duration.ofSeconds(seconds));
    }

    private TimeUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

}
