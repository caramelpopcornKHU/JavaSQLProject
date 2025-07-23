# JavaSQLProject

### 📚 **몰입코딩 아카이브**: **Git 사용법** (이클립스 협업)

---

## 컴퓨터에서 초기 git 설정

```bash

git config --global user.email "you@example.com"
git config --global user.name "Your Name"


```


## **Git 기본 명령어** (초기 설정 및 커밋)

### 1. **저장소 만들기**

```bash
git init
```

* Git 저장소를 초기화하여 버전 관리를 시작합니다.

---

### 2. **파일 추가하기**

```bash
git add .
```

* 프로젝트 폴더 내 **모든 파일**을 Git에 추가합니다. (`.`은 모든 파일을 의미)

---

### 3. **상태 확인**

```bash
git status
```

* 현재 파일 상태와 **추적된 파일**을 확인합니다. **초록색**으로 표시된 파일은 커밋 준비가 완료된 파일입니다.

---

### 4. **커밋하기**

```bash
git commit -m "프로젝트 생성"
```

* 변경 사항을 **커밋**하고, `"프로젝트 생성"`과 같은 메시지를 작성하여 이 커밋에 대한 설명을 추가합니다.

---

### 5. **원격 저장소 연결하기 (origin)**

```bash
git remote add origin https://github.com/caramelpopcornKHU/JavaSQLProject.git
```

* 원격 저장소(`origin`)와 로컬 저장소를 연결합니다. GitHub에서 제공한 URL을 입력하세요.

---

### 6. **원격 저장소 확인하기**

```bash
git remote -v
```

* 원격 저장소 주소가 제대로 연결되었는지 확인합니다.

---

### 7. **원격 저장소로 푸시하기**

```bash
git push origin master
```

* 로컬 저장소에서 작업한 내용을 원격 저장소의 `master` 브랜치로 **푸시**합니다.

---

### 8. **푸시가 안될 때 해결 방법**

```bash
git config --system --unset-all credential.helper
```

* 인증 관련 문제 발생 시 자격 증명 캐시를 초기화하여 문제를 해결할 수 있습니다.

---

---

## **조원이 할 차례: 파일 받아오기**

### 1. **작업 공간에 파일 만들기**

* **JavaSQLProject** 폴더를 작업 공간 내에 생성합니다.

---

### 2. **저장소 초기화하기**

```bash
git init
```

* 작업 공간에 **Git 저장소를 초기화**합니다.

---

### 3. **원격 저장소 연결 및 파일 받아오기**

```bash
git remote add origin https://github.com/caramelpopcornKHU/JavaSQLProject.git
git pull origin master
```

* 원격 저장소를 추가하고, **`master` 브랜치**에서 **파일을 받아옵니다**.

---

### 4. **상태 확인**

```bash
ls
```

* 파일들이 정상적으로 내려왔는지, **작업 폴더 내 파일 목록**을 확인합니다.

---

## **최초 작업 없이 받기**

### 1. **파일 받아오기**

```bash
git pull origin master
```

* **최초 작업** 없이, 이미 저장소가 설정된 경우 이 명령어로 **파일을 받기만** 하면 됩니다.

---

## **정리**

이 단계들을 통해 **로컬 저장소 초기화**, **파일 추가 및 커밋**, **원격 저장소와 연결** 후 **푸시**까지 쉽게 할 수 있습니다. 또한, **다른 사람의 작업을 받을 때**는 `git pull` 명령어를 통해 최신 상태를 가져옵니다.

---

**협업 시 주의 사항**


 **AI가 수정한 버전을 새 브랜치에서 관리**하는 건 아주 좋은 판단이에요. 팀원들과 기존 코드도 보존하면서 안전하게 비교/리뷰할 수 있습니다. 아래는 깃 명령어 단계별로 정리한 것입니다:

---

## ✅ 1. 현재 변경사항 커밋 또는 스태시

우선 수정한 코드가 있다면 저장해야 합니다.

```bash
git add .
git commit -m "AI 리팩토링: 통계 출력 모듈 분리 및 가독성 개선"
```

> 또는 커밋하고 싶지 않다면:

```bash
git stash
```

---

## ✅ 2. 새로운 브랜치 생성하고 이동

예를 들어 브랜치 이름을 `ai-refactor`로 만들고 싶다면:

```bash
git checkout -b ai-refactor
```

> 이 명령은 `ai-refactor`라는 **새 브랜치를 만들고 그 브랜치로 이동**합니다.

---

## ✅ 3. 원격 브랜치로 푸시

브랜치를 GitHub에 업로드하려면:

```bash
git push -u origin ai-refactor
```

> `-u`는 이 브랜치와 원격 브랜치를 연결해 다음부터는 `git push`만 써도 됩니다.

---

## 🔄 (선택) `stash`를 했던 경우라면 복구

```bash
git stash apply
```

---

## 🎉 결과

이제 GitHub에 `ai-refactor` 브랜치가 생기고, 그 안에 AI 리팩토링 코드가 반영됩니다.
기존 `master` 또는 `main` 브랜치와는 안전하게 분리돼 있으므로 \*\*PR(Pull Request)\*\*로 비교하거나 리뷰하기도 편해요.

---

필요하면 GitHub에서 **PR 만드는 방법**이나 \*\*브랜치 관리 전략(예: feature 브랜치 모델)\*\*도 안내해드릴게요.





* **커밋 메시지**는 팀원들이 이해할 수 있도록 간결하고 명확하게 작성해야 합니다.
* 푸시 전에 `git pull`을 통해 충돌을 방지하고 항상 최신 버전의 파일을 받도록 합니다.

---


좋아, `ai-refactor` 브랜치로 전환해서 `pull`까지 하려면 아래 순서대로 터미널에서 입력하면 돼:

---

### ✅ 1. 현재 브랜치 확인

```bash
git branch
```

현재 어떤 브랜치에 있는지 확인할 수 있어.

---

### ✅ 2. 로컬에 `ai-refactor` 브랜치가 있는지 확인

```bash
git branch --list ai-refactor
```

* **있다면** 다음 4번으로 바로 가고
* **없다면** → 3번으로 이동해서 브랜치를 가져와야 해

---

### ✅ 3. 리모트에서 `ai-refactor` 브랜치 가져오기 (로컬에 없을 때)

```bash
git fetch origin ai-refactor
git checkout -b ai-refactor origin/ai-refactor
```

---

### ✅ 4. 브랜치 전환 (이미 로컬에 있을 때)

```bash
git checkout ai-refactor
```

---

### ✅ 5. 해당 브랜치에서 최신 코드 가져오기

```bash
git pull origin ai-refactor
```

---

### ✅ 전체 명령 예시 (브랜치 없을 때부터)

```bash
git fetch origin ai-refactor
git checkout -b ai-refactor origin/ai-refactor
git pull origin ai-refactor
```

---

필요하면 다시 `master`로 돌아가려면:

```bash
git checkout master
```

원하는 브랜치로 push하거나 merge하는 방법도 알려줄 수 있어.


