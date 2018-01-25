/*
 * Copyright (c) 2018.
 * Create by LuoGui.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.luogui.baseproject;

import com.android.luogui.baseproject.bean.NewsBean2;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.LifeCache;

/**
 * describe
 * Created by  LuoGui on 2018/1/24.
 */

public interface Providers {

      /*这三个对象有什么关系呢?
    这三个对象是相互继承关系,继承关系为EvictProvider < EvictDynamicKey < EvictDynamicKeyGroup,
    这三个对象你只能传其中的一个,多传一个都会报错,按理说你不管传那个对象都一样,因为里面都保存有一个boolean字段,
    根据这个字段判断是否使用缓存不同在哪呢?如果有未过期的缓存,并且里面的boolean为false时,
    你传这三个中的哪一个都是一样的,但是在boolean为true时,这时就有区别了,
    RxCache会在Retrofit请求到新数据后,在boolean为true时删除对应的缓存删除规则是什么呢?
    还是以请求一个接口,该接口的数据会根据不同的分页返回不同的数据,并且同一个分页还要根据不同用户显示不同的数据为例
    三个都不传,RxCache会自己new EvictProvider(false);
    这样默认为false就不会删除任何缓存EvictDynamicKeyGroup 只会删除对应分页下,
    对应用户的缓存EvictDynamicKey 会删除那个分页下的所有缓存,比如你请求的是第一页下user1的数据,
    它不仅会删除user1的数据还会删除当前分页下其他user2,user3…的数据

    EvictProvider 会删除当前接口下的所有缓存,比如你请求的是第一页的数据,它不仅会删除第一页的数据,
    还会把这个接口下其他分页的数据全删除
    所以你可以根据自己的逻辑选择传那个对象,如果请求的这个接口没有分页功能,这时你不想使用缓存,
    按理说你应该传EvictProvider,并且在构造时传入true,但是你如果传EvictDynamicKey和EvictDynamicKeyGroup
    达到的效果也是一样

    这三个对象内部都保存有一个boolean类型的字段,其意思为是否驱逐(使用或删除)缓存,
    RxCache在取到未过期的缓存时,会根据这个boolean字段,考虑是否使用这个缓存,如果为true,
    就会重新通过Retrofit获取新的数据,如果为false就会使用这个缓存
    */

    /**
     * LifeCache设置缓存过期时间. 如果没有设置@LifeCache , 数据将被永久缓存理除非你使用了 EvictProvider,EvictDynamicKey or EvictDynamicKeyGroup .
     * @param newsBeanObservable
     * @param userName 驱逐与一个特定的键使用EvictDynamicKey相关的数据。比如分页，排序或筛选要求
     * @param evictDynamicKey   可以明确地清理指定的数据 DynamicKey.
     * @return
     */

    @LifeCache(duration = 1,timeUnit = TimeUnit.MINUTES)
    Observable<NewsBean2> getString(Observable<NewsBean2> newsBeanObservable, DynamicKey userName, EvictDynamicKey evictDynamicKey);

}
