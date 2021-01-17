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

        def get_argument():
            parser = argparse.ArgumentParser()
            # 회원 ID, account, pw 받기 -> playlist, 협업 필터링 기반 추천 시스템에서 사용
            parser.add_argument('-i', '--id', type=int, required=True, nargs=1)  # ID
            parser.add_argument('-a', '--account', type=str, required=True, nargs=1)  # account

            args = parser.parse_args()

            ID, account = args.id, args.account

            print(ID, account, "jinwoo") # print 로 java에서는 값을 가져옴

            return ID, account

        ID, account = get_argument()

        result = ID + account




if __name__ == '__main__':
    Code_test()