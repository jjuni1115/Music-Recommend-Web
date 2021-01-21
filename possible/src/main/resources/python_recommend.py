import argparse
from collections import defaultdict
import sys
import pymysql

# pymysql 패키지 설치 필요
class Code_test():
    def __init__(self):
        super().__init__()

        self.recommend()

    def recommend(self):
        def sql(video_ID):
            # 사용자 간 관계를 토대로 해당 노래를 플레이리스트에 넣은 모든 사용자의 플레이리스트를 분석하여 빈도가 높은 곡부터 나열
            sql_recommend_by_user = "SELECT music_ID, COUNT(playlist_ID) AS dupl, playlist_ID" + \
                "FROM rel" + \
                "GROUP BY music_ID" + \
                "HAVING playlist_ID IN (" + \
                   "SELECT ID" + \
                   "FROM playlist" + \
                   "WHERE user_ID IN (" + \
                      "SELECT distinct user_ID" + \
                      "from playlist, rel, music" + \
                      "where playlist.ID = rel.playlist_ID AND music.ID = rel.music_ID AND music.videoid = \"" + video_ID + "\"))" + \
                "ORDER BY dupl DESC;"

            # 플레이 리스트를 기준으로 해당 노래가 있는 플레이 리스트의 모든 곡들을 분석하여 빈도가 높은 곡부터 나열
            sql_recommend_by_playlist = "SELECT music_ID, count(playlist_ID) AS dupl, playlist_ID" + \
                "FROM rel" + \
                "GROUP BY music_ID" + \
                "HAVING playlist_ID IN (" + \
                   "SELECT playlist_ID" + \
                   "FROM rel, music" + \
                   "WHERE rel.music_ID = music.ID AND videoid = \"" + video_ID + "\")" + \
                "ORDER BY dupl desc;"

        def get_argument():
            parser = argparse.ArgumentParser()
            # 회원 ID, account, pw 받기 -> playlist, 협업 필터링 기반 추천 시스템에서 사용
            parser.add_argument('-i', '--id', type=int, required=True, nargs=1)  # ID
            parser.add_argument('-a', '--account', type=str, required=True, nargs=1)  # account
            parser.add_argument('-m', '--music', type=str, required=True, nargs=1)  # music now

            args = parser.parse_args()

            ID, account, music = args.id, args.account, args.music

            print(ID, account, music)  # print 로 java에서는 값을 가져옴

            return ID, account, music

        ID, account, music = get_argument()



        result = ID + account




if __name__ == '__main__':
    Code_test()