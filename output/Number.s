extern __malloc
extern __exception
extern __THIS
extern __createNullString
; *****method _java.lang.Number_METHOD_intValue# has no body *****
; *****Start ConstructorDeclarationNode *****
global _java.lang.Number_CONSTRUCTOR_Number#
_java.lang.Number_CONSTRUCTOR_Number#:
mov ebp, esp
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; calls implicit constructor for Number
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
