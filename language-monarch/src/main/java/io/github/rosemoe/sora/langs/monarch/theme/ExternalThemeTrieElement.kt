/*******************************************************************************
 * Copyright (c) 2015-2017 Angelo ZERR.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * <p>
 * SPDX-License-Identifier: EPL-2.0
 * <p>
 * Contributors:
 * Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 * Sebastian Thomschke (Vegard IT GmbH) - add previousLineText support
 *
 ******************************************************************************/

package io.github.rosemoe.sora.langs.monarch.theme

class ExternalThemeTrieElement(
    val mainRule: ThemeTrieElementRule,
    children: Map<String, ExternalThemeTrieElement> = mapOf()
) {
    val children = children.toMutableMap()
}