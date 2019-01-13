package com.baifenjy.service;

import com.baifenjy.vo.Song;

public interface SongService {

    Song pageQuery(int i, int pageSize, String title);

}
