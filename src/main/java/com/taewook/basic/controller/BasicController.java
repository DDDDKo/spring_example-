package com.taewook.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taewook.basic.service.BasicService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

// @RestController : JSON 형태의 Response Body를 반환하는 Controller 임을 명시\
// @RestController = @Controller + @ResponseBody
@RestController
// @RequestMapping() : HTTP 요청에 클래스와 메서드를 매핑하기 위한 어노테이션
// HTTP GET localhost:4000/main/**
// @RequestMapping(value = "/main", method = {RequestMethod.GET})

@RequestMapping("/main")
// @RequiredArgsConstructor : final로 선언된 필드 변수에 대해서 생성자를 자동으로 작성함
@RequiredArgsConstructor
public class BasicController {

    // 의존성 역전의 법칙 위배
    //  private BasicServiceImplement service = new BasicServiceImplement();

    // 의존성을 내부에서 주입하기 때문에 안 좋음
    //  private BasicService service = new BasicServiceImplement();

    // 생성자를 통해 의존성을 외부에서 주입
    // private BasicService service;

    // 의존성 역전 (DI) : 
    // - 해당 클래스에 필요한 의존성을 외부(클래스의 인스턴스를 생성하는 위치)에서 주입
    // - 생성자를 통한 의존성 주입, setter 메서드를 통한 의존성 주입, 필드를 통한 의존성 주입
    // - spring framework에서 의존성 주입은 제엉의 역전 (IoC)를 통해서 spring framework가 진행(@Component를 사용해서(Spring Bean으로 등록된 Class에 한해서))

    // @Autowir록된 Spring Bean을 제어의 역전을 통해서 의존성을 주입하는 방법
    // - 단, 생성자를 통한 의존성 주입에는 @Autowired를ed : 등 생략해도 됨

    // 생성자를 통한 의존성 주입을 Spring에서 권장 (이유: 의존성이 주입되지 않은 경우가 존재할 수 없기 때문)

    // @Autowired 원래 걸아야 하지만 생성자로 의존성을 주입
    //     this.service = service;할 때는 안 걸어도 사용 가능
    // public BasicController(BasicService service) {
    // }

    // 의존성 주입시 클래스로 직접 참조 변수를 만들지 않고 인터페이스로 간접적으로 만드는 이유 (****중요****): 
    // 고수준의 모듈에서 저수준의 모듈을 직접 참조하지 않고 추상화를 통해 간접 참조함으로써 각 모듈간의 결합도를 낮춤 -> 코드의 재사용성 향상, 유지보수성 향상
    private final BasicService service;
    
    // HTTP GET localhost:4000/main/
    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public String getHello() {
        return service.getHello();
    }

    // HTTP GET Method : 클라이언트가 서버로부터 데이터를 받기를 원할 때 사용하는 메서드

    // @GetMapping() : RequestMapping 기능을 Get Method에 한정시킨것 (가독성 + 안전성  높여줌)
    @GetMapping("/apple")
    public String getApple () {
        return service.getApple();
    }

    // HTTP POST Method : 클라이언트가 서버에 데이터를 작성하기 원할 때 사용하는 메서드

    // @PostMapping() : RequestMapping 기능을 Post Method에 한정시킨것 (가독성 + 안전성  높여줌)
    @PostMapping("/apple")
    public String postApple() {
        return "Post Mapping으로 만든 메서드";
    }

    // HTTP PUT Method : 클라이언트가 서버에 있는 리소스 전체를 수정하고 싶을 때 사용하는 메서드

    // @PutMapping() : RequestMapping 기능을 Put Method에 한정시킨것 (가독성 + 안전성 높여줌)
    @PutMapping("/apple")
    public String putApple() {
        return "Put Mapping으로 만든 메서드";
    }

    // HTTP PATCH Method : 클라이언트가 서버에 있는 리소스 일부를 수정하고 싶을 때 사용하는 메서드

    // @PatchMapping() : RequestMapping 기능을 Patch Method에 한정 시킨것 (가독성 + 안전성 높여줌)
    @PatchMapping("/apple")
    public String patchApple() {
        return "Patch Mapping으로 만든 메서드";
    }

    // HTTP DELETE Method : 클라이언트가 서버에 있는 리소스를 삭제하고 싶을 때 사용하는 메서드

    // @DeleteMapping() : RequestMapping 기능을 Delete Method에 한정 시킨것 (가독성 + 안전성 높여줌)
    @DeleteMapping("/apple")
    public String deleteApple() {
        return "Delete Mapping으로 만든 메서드";
    }
    // Method + URL Parttern 이 중복되면 런타임 중에 에러가 발생
    // @DeleteMapping("/apple")
    // public String deleteApple1() {
    //     return "Delete Mapping으로 만든 메서드";
    // }
}
