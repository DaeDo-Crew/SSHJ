# SSHJ-api
코로나로 인해 위기에 처한 동아리와 새내기를 위해 동아리 소규모 모임, 교육 등을 주최하고, 그에 참여하는 웹/앱 서비스 플랫폼

## Use Cases
- 이벤트 모임 등록 (동아리)
  1. 동아리 유저는 새로운 이벤트를 등록할 수 있다.
  2. 이벤트에대한 사진과 동영상을 같이 등록할 수 있다.
  3. 참가 인원을 설정할 수 있다.
  4. 신청한 인원에게 쪽지를 보낼 수 있다.
 
- 이벤트 모임 참가 (학생)
  1. 참여하고 싶은 동아리 유저를 선택하여 참여할 수 있다.
  2. 이벤트에대한 사진과 동영상을 같이 확인할 수 있다.
  3. 후기 댓글 별점을 반영할 수 있다.

- 알람 기능 제공
  1. 이벤트 등록 및 취소시 알람을 제공한다.
  2. 이벤트 시작전후 알람 기능 제공한다.

- 쪽지 기능 제공
  1. 이벤트 문의사항 발생시 동아리 유저에게 쪽지를 보낼 수 있다.

- 신고 기능 제공
  1. No Show를 하거나 정책에 어긋나는 행위를 했을시 사용자는 신고할 수 있다. (No Show에 대해서는 후에 더 깊은 이야기가 필요함)

- 내 프로필 꾸미기 제공
  1. 동아리 유저는 자신 동아리에 대한 사진과 동영상, 글귀를 올림으로써 내 프로필을 꾸밀 수 있다.
  2. 동아리 유저는 사진 및 동영상 저장 기능 제공
  3. 사용자 유저는 닉네임을 설정할 수 있다.

## Running the app

``` bash
./gradlew bootRun
```

## Swagger

explore swagger docs
`http://localhost:9090/swagger-ui.html`
