extern __malloc
extern __exception
extern __THIS
extern __createNullString
; *****Start ConstructorDeclarationNode *****
global _java.lang.Class_CONSTRUCTOR_Class#
_java.lang.Class_CONSTRUCTOR_Class#:
mov ebp, esp
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; calls implicit constructor for Class
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
