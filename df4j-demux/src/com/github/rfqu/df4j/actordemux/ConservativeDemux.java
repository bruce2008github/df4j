/*
 * Copyright 2011 by Alexei Kaigorodov
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.github.rfqu.df4j.actordemux;

import com.github.rfqu.df4j.core.Link;

/** Associative array of decoupled actors. Yes, this is tagged dataflow.
 * 
 * @author rfq
 *
 * @param Tag type of key
 * @param M type of messages for actors
 * @param H type of delegate
 */
public abstract class ConservativeDemux<Tag, M extends Link, H extends Delegate<Tag, M>>
	extends AbstractDemux<Tag, M, H>
{
    protected AbstractDelegator<Tag, M,H> createDelegator(Tag tag) {
        return new AbstractDelegator<Tag, M,H>(tag) {

            @Override
            protected void act(M message) throws Exception {
                handler.token.act(tag, message);
            }
            
        };
    }
}
