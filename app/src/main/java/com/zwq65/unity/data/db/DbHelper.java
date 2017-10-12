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

package com.zwq65.unity.data.db;


import com.zwq65.unity.data.db.model.Picture;

import java.util.List;

import io.reactivex.Observable;


/**
 * ================================================
 * 网络访问接口
 * <p>
 * Created by NIRVANA on 2017/01/27.
 * Contact with <zwq651406441@gmail.com>
 * ================================================
 */
public interface DbHelper {

    Observable<Long> insertPicture(final Picture picture);

    Observable<Long> deletePicture(final String id);

    Observable<Boolean> isPictureExist(final String id);

    //获取用户收藏的图片
    Observable<List<Picture>> getCollectionPictures();

}
