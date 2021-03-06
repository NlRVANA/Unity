/*
 *    Copyright [2017] [NIRVANA PRIVATE LIMITED]
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.zwq65.unity.data.db


import com.zwq65.unity.data.db.model.Picture
import io.reactivex.Observable

/**
 * ================================================
 * 网络访问接口
 * <p>
 * Created by NIRVANA on 2017/01/27.
 * Contact with <zwq651406441@gmail.com>
 * ================================================
 */
interface DbHelper {

    /**
     * 获取用户收藏的图片
     *
     * @return Observable<>
     */
    val collectionPictures: Observable<List<Picture>>

    /**
     * 保存图片
     *
     * @param picture picture
     * @return Observable<Long>
     */
    fun savePicture(picture: Picture): Observable<Long>

    /**
     * 删除图片
     *
     * @param id picture'id
     * @return Observable<Long>
     */
    fun deletePicture(id: String): Observable<Long>

    /**
     * 判断图片是否保存
     *
     * @param id picture'id
     * @return Observable<Long>
     */
    fun isPictureExist(id: String): Observable<Boolean>

}
