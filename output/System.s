extern __malloc
extern __exception
extern __THIS
extern __createNullString
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.System_METHOD_gc#
_java.lang.System_METHOD_gc#:
mov ebp, esp
; *****END MethodHeader *****
; *****implicit return at end of void method's body *****
mov esp, ebp
ret ; return

; *****END MethodDeclaration *****
; *****Start ConstructorDeclarationNode *****
global _java.lang.System_CONSTRUCTOR_System#
_java.lang.System_CONSTRUCTOR_System#:
mov ebp, esp
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; calls implicit constructor for System
extern _java.lang.Object_CONSTRUCTOR_Object#
call _java.lang.Object_CONSTRUCTOR_Object# ; call constructor
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

; *****initialize nonstatic fields *****
; *****implicit return at end of constructor *****
mov esp, ebp
ret ; return

; *****END ConstructorDeclarationNode *****
