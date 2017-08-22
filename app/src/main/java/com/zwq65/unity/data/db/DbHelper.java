/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.zwq65.unity.data.db;


import com.zwq65.unity.data.db.model.Picture;
import com.zwq65.unity.data.db.model.User;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by janisharali on 08/12/16.
 */

public interface DbHelper {

    Observable<Long> insertUser(final User user);

    Observable<List<User>> getAllUsers();

    Observable<Long> insertPicture(final Picture picture);

    Observable<Long> deletePicture(final String id);

    Observable<Boolean> isPictureExist(final String id);

    //获取用户收藏的图片
    Observable<List<Picture>> getCollectionPictures();

}
