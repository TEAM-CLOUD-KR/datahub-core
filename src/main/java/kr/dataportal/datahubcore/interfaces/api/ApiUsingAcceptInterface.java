/*
    Copyright (c) 2021 Aaron(JIN, Taeyang).
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the GNU Lesser General Public License, version 3
    which accompanies this distribution, and is available at
    https://www.gnu.org/licenses/lgpl-3.0.html
    
    Contributors:
        Aaron(JIN, Taeyang) - 
*/

package kr.dataportal.datahubcore.interfaces.api;

import kr.dataportal.datahubcore.domain.api.ApiUsingList;

import java.util.List;

public interface ApiUsingAcceptInterface {
    int save(ApiUsingList apiUsingList);

    ApiUsingList findByApiSeq(int apiSeq);

    ApiUsingList findByApiAndServiceKey(int apiSeq, String serviceKey);

    List<ApiUsingList> findByPublisher(int userSeq);

    List<ApiUsingList> findByRequestUser(int userSeq);

    ApiUsingList findByApiAndRequestUser(int apiSeq, int userSeq);
}
